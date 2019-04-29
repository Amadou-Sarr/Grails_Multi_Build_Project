package app.admin.jobsboard

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class JobController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(["permitAll"])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Job.list(params), model:[jobCount: Job.count()]
    }
    
    @Secured(["permitAll"])
    def show(Job job) {
        respond job
    }

    @Secured(['ROLE_CUSTOMER'])
    def listAll(Integer max){
        params.max = Math.min(max ?: 10, 100)
        respond Job.list(params), model:[JobCount: job.count()]
    }

    @Secured(['permitAll'])
    def listFeatured(Integer max){
        params.max = 1
        params.order = "desc"
        params.tilte = "sort"

        respond Job.list(params), model:[JobCount: job.count()]

    }

    @Transactional
    def save(Job job) {
        if (job == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (job.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond job.errors, view:'create'
            return
        }

        job.save flush:true

        respond job, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Job job) {
        if (job == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (job.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond job.errors, view:'edit'
            return
        }

        job.save flush:true

        respond job, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Job job) {

        if (job == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        job.delete flush:true

        render status: NO_CONTENT
    }
}
