package kz.mouzitoto.gransoftwaretest2.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Родительский репо
 */

@NoRepositoryBean
public interface GranSoftwareTest2Repository<T, ID extends Serializable> extends JpaRepository<T, ID> {
}
