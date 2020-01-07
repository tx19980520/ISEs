package wd.pledge.guarantee.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wd.pledge.guarantee.entity.Pledge;
import wd.pledge.guarantee.util.LogicalState;
import wd.pledge.guarantee.util.PhysicalState;

import javax.transaction.Transactional;

@Repository
public interface PledgeRepository extends CrudRepository<Pledge, Integer> {

    @Modifying
    @Transactional
    @Query("update Pledge pledge set pledge.logicalState = ?2 where pledge.pledgeId = ?1")
    public void updatePledgeLogicalState(Integer id, LogicalState logicalState);

    @Modifying
    @Transactional
    @Query("update Pledge pledge set pledge.physicalState = ?2 where pledge.pledgeId = ?1")
    public void updatePledgePhysicalState(Integer id, PhysicalState physicalState);

    Pledge findByName(String name);
}
