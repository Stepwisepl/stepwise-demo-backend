package pl.stepwise.demo.runner.repository

import org.springframework.data.jpa.repository.JpaRepository
import pl.stepwise.demo.runner.model.RobotStatus

interface RobotStatusRepository : JpaRepository<RobotStatus, Long>