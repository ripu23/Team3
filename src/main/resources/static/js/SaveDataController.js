(function(){
var app = angular.module("mainApp")
app.controller("SaveDataController",[
  '$scope',
  'availableObjects',
  'ObjectService',
  function($scope, availableObjects, ObjectService){
  console.log("Reached SaveDataController");
  $scope.availableObjects;
  $scope.populateObj;
  $scope.toBeSaved;
  $scope.toBeSaved.attributes = [];
  $scope.selected;
  if(availableObjects){
    $scope.availableObjects = availableObjects;
  }

  $scope.populateForm = function(data){
    ObjectService.getObjectDetails(data).then(function(response){
      $scope.populateObj = response.data;
    }, function(err){
      if(err) throw err;
      alertify.error("Population failed");
    })
  }

  $scope.updateObject = function(label, val){
    let foundObject = _.find($scope.toBeSaved.attributes, function(val, key){
      return key == label;
    })
  }
  $scope.save = function (data) {
    console.log(data);
  }
  $scope.uploadCsv = function(data){

  }
}]);
})();
