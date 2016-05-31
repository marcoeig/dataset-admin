package at.eigletsberger.masterthesis.dataset.admin

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * @author Marco Eigletsberger, 30.05.16.
 */

@SpringBootApplication
open class DatasetAdminApp {

}

fun main(args: Array<String>) {
    SpringApplication.run(DatasetAdminApp::class.java, *args)
}