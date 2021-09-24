package pl.stepwise.demo.runner.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
open class Point(
    open var x: Int,
    open var y: Int,
    open var z: Int
) {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    open var id: Long? = null
}