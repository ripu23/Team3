var app = angular.module("mainApp");

app.service("EventCrudService", function($http) {
  this.saveModule = function saveModule(data) {
    return $http({
      method: 'POST',
      url: '/createEvent/',
      data: {
        eventName: data.moduleName,
        attributes: data.attributes,
        objectName: data.objectName
      }
    })
  }
})
