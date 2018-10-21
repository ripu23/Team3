var app = angular.module("mainApp");

app.factory("EventService", function($http) {

  this.getAllEvents = function getAllEvents() {
    return $http.get("/event/getAll", function(response) {
      return response.data;
    })
  }
  this.getEventsForObject = function getEventsForObject(){
    return $http.get("/event/getEventsForObject", function(response) {
      return response.data;
    })
  }
  this.getEventDetails = function getEventDetails(data){
    return $http({
      method: 'GET',
      url: '/event/getEventDetails',
      params: {moduleName: data}
    })
  }
})
