package com.claimapplicationportal.admin.repository;

import com.claimapplicationportal.admin.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepo extends JpaRepository<Template,Integer>{
}
