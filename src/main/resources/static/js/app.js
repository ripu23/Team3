let homeApp = angular.module("mainApp", ["ngRoute", "xeditable"]);
homeApp.run(function(editableOptions, editableThemes) {
  editableOptions.theme = 'bs3';
	editableThemes['bs3'].submitTpl = '<button type="submit" class="btn btn-primary btn-sm"><i class="fa fa-check-square" aria-hidden="true"></i></button>';
});

homeApp.config(['$routeProvider', function($routeProvider){
	$routeProvider
	.when('/',{
		templateUrl: 'templates/CreateModule.html',
		controller : 'CreateModuleController'
	})
	.otherwise({
		redirectTo: '/'
	})
}]);
