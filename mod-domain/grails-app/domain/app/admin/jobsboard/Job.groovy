package app.admin.jobsboard

class Job {

    String title
    String description
    Date expirationDate

    String jobUrl
    String applyInstructions
    String contactEmail

    String salaryEstimate

    Boolean active
    Boolean remote

    Date dateCreated
    Date lastUpdated

    // Many to One
    Type type
    Publisher publisher

    // One to Many
    static hasMany = [tags: Tag]

    static constraints = {
        title nullable: false, blank: false
        description nullable: false, blank: false, type: 'text'
        contactEmail nullable: false, blank: false, email: true
        jobUrl nullable: false, blank: false
        contactEmail nullable: false, blank: false, email: true
        applyInstructions nullable: false, blank: false
        salaryEstimate nullable: true, blank: true, size: 10..100 * 1024
        active defaultValue: false
        expirationDate nullable: false
        remote nullable: false
    }
}
