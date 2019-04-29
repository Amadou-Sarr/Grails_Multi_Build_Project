//= wrapped

angular
    .module("app.admin.jobsboard.publisher")
    .factory("Publisher", Publisher);

function Publisher($resource, dateFilter) {
    var transformer = getObjectToFormDataConverter(dateFilter);
    var Publisher = $resource(
        "publisher/:id",
        {"id": "@id"},
        {"save": {method: "POST", transformRequest: transformer, headers: {'Content-Type': undefined}},
         "update": {method: "PUT", transformRequest: transformer, headers: {'Content-Type': undefined}},
         "query": {method: "GET", isArray: true},
         "get": {method: 'GET'}}
    );
    
    Publisher.list = Publisher.query;

    Publisher.prototype.toString = function() {
        return 'app.admin.jobsboard.Publisher : ' + (this.id ? this.id : '(unsaved)');
    };

    return Publisher;
}

function getObjectToFormDataConverter(dateFilter) {

    return function(obj) {
        var fd = new FormData();

        function populateFormData(obj, fd, parentProperty) {
            for(var property in obj) {
                if(obj.hasOwnProperty(property)) {
                    if (parentProperty) {
                        property = parentProperty + "." + property;
                    }
                    var value = undefined;
                    property.split('.').forEach(function(prop) {
                        value = value ? value[prop] : obj[prop];
                    });
                    // if the property is an object, but not a File,
                    // use recursivity.
                    if(typeof value === 'object' && !(value instanceof File) && !(value instanceof Date)) {
                        populateFormData(value, fd, property);
                    } else {
                        if (value instanceof Date) {
                            fd.append(property, dateFilter(value, "yyyy-MM-dd'T'HH:mm:ss.sss'Z'", 'UTC'));
                        } else {
                            fd.append(property, value);
                        }
                    }
                }
            }
        }

        populateFormData(obj, fd);

        return fd;
    }

}
