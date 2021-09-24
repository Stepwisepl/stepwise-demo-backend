package pl.stepwise.demo.definition.repository

import org.springframework.data.jpa.repository.JpaRepository
import pl.stepwise.demo.definition.model.RobotDefinition

interface RobotDefinitionRepository : JpaRepository<RobotDefinition, Long>