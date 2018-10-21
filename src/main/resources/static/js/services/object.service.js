var app = angular.module("mainApp");

app.factory("ObjectService", function($http){
  return {
    getAllObjects: function(){
      return $http.get("/object/getAll").then(function(response){
        return response.data;
      })
    }
  }
});
