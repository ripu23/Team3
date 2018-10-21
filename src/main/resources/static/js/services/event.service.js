var app = angular.module("mainApp");

app.factory("EventService", function($http) {
  return {
    getAllEvents: function() {
      return $http.get("/event/getAll", function(response) {
        return response.data;
      })
    }
  }
})
