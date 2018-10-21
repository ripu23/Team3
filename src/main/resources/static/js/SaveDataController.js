(function(){
var app = angular.module("mainApp")
app.controller("SaveDataController",[
  '$scope',
  'availableObjects',
  'ObjectService',
  function($scope, availableObjects, ObjectService){
  console.log("Reached SaveDataController");
  $scope.availableObjects;
  if(availableObjects){
    $scope.availableObjects = availableObjects;
  }
  $scope.populateObj;
  $scope.populateForm = function(data){
    console.log(data);
    ObjectService.getObjectDetails(data).then(function(response){
      console.log(response);
      $scope.populateObj = response.data;
    }, function(err){
      if(err) throw err;
    })
  }

}]);

})();
