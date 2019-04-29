package app.admin

import app.admin.jobsboard.Tag
import app.admin.jobsboard.Type
import app.admin.security.Role
import app.admin.security.User
import app.admin.security.UserRole

class BootStrap {


    def init = { servletContext ->
        populateUser()
        populateTypes()
        populateDefaultTags()

    }

    def populateUser  = {

        def operatorRole = new Role(authority: 'ROLE_OPERATOR').save()
        def adminRole = new Role(authority: 'ROLE_ADMIN').save()
        def customerRole = new Role(authority: 'ROLE_CUSTOMER').save()


        def operatorUser =  new User(username: 'operataor', password: 'password123').save()
        def customerUser =  new User(username: 'customer', password: 'password123').save()
        def adminUser =  new User(username: 'admin', password: 'password123').save()

        UserRole.create adminUser, adminRole
        UserRole.create operatorUser, operatorRole
        UserRole.create customerUser, customerRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        assert User.count()== 3
        assert Role.count() == 3
        assert UserRole.count() == 3

    }

     def populateTypes() {
         if(Type.count() == 0) {
             def types = ['Commission', 'Volunteer', 'Part-time',
                            'Internship', 'Full-time', 'Temporary',
                            'Apprenticeship', 'Contract', 'Permanent']

             types.each {
                 def type = new Type(name: it, description: "${it.replace('-', ' ')} job")
                 type.save(flash: true, failOnError: true)
                 }
             }
         }

     def populateDefaultTags() {
         if(Tag.count() == 0) {
             def defaultTags = ['Mobile', 'Engineer', 'Dev', 'Remote', 'Senior']
             defaultTags.each {
                 def tag = new Tag(name: it)
                 tag.save(flash: true, failOnError: true)
                 }
             }
         }


    def destroy = {
    }
}
