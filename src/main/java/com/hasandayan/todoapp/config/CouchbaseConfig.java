package com.hasandayan.todoapp.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.CouchbaseClientFactory;
import org.springframework.data.couchbase.SimpleCouchbaseClientFactory;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.convert.CouchbaseCustomConversions;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.data.couchbase.repository.config.RepositoryOperationsMapping;

import com.hasandayan.todoapp.model.AppUser;


@Configuration
@EnableCouchbaseRepositories(basePackages = { "com.hasandayan.todoapp.repository" })
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Value("${app.couchbase.connection-string}")
	private String connectionString;
	
	@Value("${app.couchbase.username}")
	private String username;
	
	@Value("${app.couchbase.password}")
	private String password;
	
	@Value("${app.couchbase.bucket-todo}")
	private String bucketTodo;
	
	@Value("${app.couchbase.bucket-user}")
	private String bucketUser;
	

	@Override
	public String getConnectionString() {
		return connectionString;
	}

	@Override
	public String getUserName() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getBucketName() {
		return bucketTodo;
	}

	@Override
	public void configureRepositoryOperationsMapping(RepositoryOperationsMapping mapping) {

		try {
			mapping.mapEntity(AppUser.class, getCouchbaseTemplate(bucketUser));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private CouchbaseTemplate getCouchbaseTemplate(String bucketName) throws Exception {

		CouchbaseTemplate template = new CouchbaseTemplate(couchbaseClientFactory(bucketName),
				mappingCouchbaseConverter(couchbaseMappingContext(customConversions()),
						new CouchbaseCustomConversions(Collections.emptyList())));

		template.setApplicationContext(applicationContext);

		return template;
	}

	private CouchbaseClientFactory couchbaseClientFactory(String bucketName) {
		return new SimpleCouchbaseClientFactory(couchbaseCluster(couchbaseClusterEnvironment()), bucketName,
				this.getScopeName());
	}
}