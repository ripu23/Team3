(function(){
var app = angular.module("mainApp")
app.controller("SaveEventController",[
  '$scope',
  'availableObjects',
  'ObjectService',
  'EventService',
  function($scope, availableObjects, ObjectService, EventService){
  console.log("Reached SaveEventController");
  $scope.availableObjects;
  if(availableObjects){
    $scope.availableObjects = availableObjects;
  }
  $scope.populateObj;
  $scope.eventsForObject;
  $scope.populateEventsForForm = function(data){
    EventService.getEventsForObject(data).then(function(response){
      $scope.eventsForObject = response.data;
    }, function(err){
      if(err) throw err;
      alertify.error("Population failed");
    })
  }
  
  $scope.populateFormData = function(data){
    EventService.getEventDetails(data).then(function(response){
      $scope.populateObj = response.data;
    }, function(err){
      if(err) throw err;
      alertify.error("Population failed");
    })
  }

  $scope.save = function (data) {
    console.log(data);
  }
  $scope.uploadCsv = function(data){

  }
}]);

})();
