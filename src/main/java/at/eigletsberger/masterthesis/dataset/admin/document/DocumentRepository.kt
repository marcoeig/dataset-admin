package at.eigletsberger.masterthesis.dataset.admin.document

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RestResource

/**
 * @author Marco Eigletsberger, 30.05.16.
 */
interface DocumentRepository : JpaRepository<Document, Long> {
    fun findByDeleted(@Param("deleted") deleted: Boolean, page: Pageable): Page<Document>

    @RestResource(path = "by-dataset1", rel = "by-dataset1")
    fun findByDataset1(@Param("dataset") dataset: Boolean, page: Pageable): Page<Document>

    @RestResource(path = "by-dataset2", rel = "by-dataset2")
    fun findByDataset2(@Param("dataset") dataset: Boolean, page: Pageable): Page<Document>

    @RestResource(path = "by-dataset3", rel = "by-dataset3")
    fun findByDataset3(@Param("dataset") dataset: Boolean, page: Pageable): Page<Document>

    @RestResource(path = "by-term", rel = "by-term")
    @Query("select d from Document d where (CAST(d.cluster as string) like %:searchTerm% or LOWER(d.title) like %:searchTerm% or LOWER(d.text) like %:searchTerm%) and d.deleted=false")
    fun findByTerm(@Param("searchTerm") searchTerm: String, page: Pageable): Page<Document>
}