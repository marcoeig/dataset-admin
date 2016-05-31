package at.eigletsberger.masterthesis.dataset.admin.document

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * @author Marco Eigletsberger, 30.05.16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity class Document @JsonCreator constructor(
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    @Column(name = "id")
    @JsonProperty("id")
    var id: Long = 0,
    @Column(name = "cluster")
    @JsonProperty("cluster")
    var cluster: Long = -1,
    @Column(name = "deleted")
    @JsonProperty("deleted")
    var deleted: Boolean = false,
    @Column(name = "text", columnDefinition = "text")
    @JsonProperty("text")
    var text: String = "",
    @Column(name = "title", columnDefinition = "text")
    @JsonProperty("title")
    var title: String = "",
    @Column(name = "dataset1")
    @JsonProperty("dataset1")
    var dataset1: Boolean = false,
    @Column(name = "dataset2")
    @JsonProperty("dataset2")
    var dataset2: Boolean = false,
    @Column(name = "dataset3")
    @JsonProperty("dataset3")
    var dataset3: Boolean = false
)