var app = angular.module("mainApp");

app.service("ObjectService", function($http){

    this.getAllObjects = function getAllObjects() {
      return $http.get("/object/getAllObjects").then(function(response){
        return response.data;
      })
    }
    this.getObjectDetails = function getObjectDetails(data){
      return $http({
        method: 'GET',
        url: '/object/getObjectDetails',
        params: {moduleName: data.moduleName}
      })
    }

});
