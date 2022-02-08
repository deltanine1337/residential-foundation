package com.test.task.repos;

import com.test.task.model.Tenant;
import org.springframework.data.repository.CrudRepository;

public interface TenantRepo extends CrudRepository<Tenant, Long> {
}
