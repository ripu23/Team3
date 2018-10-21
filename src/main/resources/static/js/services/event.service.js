var app = angular.module("mainApp");

app.service("EventService", function($http, Upload) {

  this.getAllEvents = function getAllEvents() {
    return $http.get("/event/getAll", function(response) {
      return response.data;
    })
  }
  this.getEventsForObject = function getEventsForObject(data){
    return $http({
      method: 'GET',
      url: '/event/getEventsForObject',
      params: {objectName: data}
    })
  }
  this.getEventDetails = function getEventDetails(data){
    return $http({
      method: 'GET',
      url: '/event/getEventDetails',
      params: {moduleName: data}
    })
  }

  this.createEvent = function createEvent(data){
    return $http({
      method: 'POST',
      url: '/event/createEvent',
      data: data
    })
  }

  this.saveCsv = function saveCsv(data){
    Upload.upload({
      url: '/event/saveFile',
      file: data.file,
      eventName: data.eventName,
      objectName: data.objectName
    }).progress(function(e) {}).then(function(data, status, headers, config) {
      console.log(data);
    });
  }
})
