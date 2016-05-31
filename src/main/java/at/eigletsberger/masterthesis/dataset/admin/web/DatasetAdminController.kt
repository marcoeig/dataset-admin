package at.eigletsberger.masterthesis.dataset.admin.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * @author Marco Eigletsberger, 30.05.16.
 */
@Controller
open class DatasetAdminController {

    @RequestMapping("/")
    fun index() = "index"
}