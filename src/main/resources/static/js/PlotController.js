(function() {
  var app = angular.module("mainApp");

  app.controller("PlotController", [
    '$scope',
    function($scope) {
      console.log("Reached plot controller");
      let margin = {
        top: 20,
        bottom: 30,
        left: 10,
        right: 10
      };
      let svgHeight = 500;
      let svgWidth = 600;
      let innerWidth = svgWidth - margin.left - margin.right;
      let innerHeight = svgHeight - margin.bottom - margin.top;
      let donations;
      let donors;
      EventService.getAllEvents().then(function(response){
        donations = response.data;
        alertify.success("Successfuly imported data");
        ObjectService.getAllObjects().then(function(response){
          donors = response.data;
          alertify.success("Successfuly imported data");
          callback(donations, donors);
        }, function(err){
          alertify.error("Error importing the data");
          if(err){
            throw err;
          }
        })
      }, function(err){
        alertify.error("Error importing the data");
        if(err){
          throw err;
        }
      })



      function callback(donations, donors) {

        var color = d3.scaleLinear().domain([0, 186]).range([0, 1]);
        var svg = d3.select('#bubble').append('svg')
          .attr('height', svgHeight)
          .attr('width', svgWidth)
          .attr('transform', 'translate(0,0)');


        svg.append('text')
          .attr('x', svgWidth / 2)
          .attr('y', margin.top)
          .attr("text-anchor", "middle")
          .style("font-size", "15px")
          .text("Donations");

        var g = svg.selectAll('g').data(countryWiseListing).enter().append('g');
        var scaleForRadius = d3.scaleSqrt().domain([0, 186]).range([0, 50]); //Maximum count is 186
        var simulation = d3.forceSimulation()
          .force('force_x', d3.forceX(svgWidth / 2).strength(0.05))
          .force('force_y', d3.forceY(svgHeight / 2).strength(0.05))
          .force('anti_collide', d3.forceCollide(function(d) {
            return scaleForRadius(d.count) + 10;
          }));


        var description = d3.select("body")
          .append("div")
          .style("position", "absolute")
          .style("z-index", "10")
          .style("visibility", "hidden")
          .style("color", "white")
          .style("padding", "8px")
          .style("background-color", "rgba(0, 0, 0, 0.75)")
          .style("border-radius", "6px")
          .style("font", "12px sans-serif")
          .text("tooltip");

        var circles = g.append('circle')
          .attr('class', 'player')
          .attr('r', function(d) {
            return scaleForRadius(d.count); //for varying circle radius
          })
          .style("fill", function(d) {
            return d3.interpolateGreens(color(d.count));
          })
          .on('mouseover', function(d) {
            description.text(alphaCode[d.country]);
            description.style("visibility", "visible");
          })
          .on("mousemove", function() {
            return description.style("top", (d3.event.pageY - 10) + "px").style("left", (d3.event.pageX + 10) + "px");
          })
          .on("mouseout", function() {
            return description.style("visibility", "hidden");
          })
          .on('click', drillDown);

        function mouseOver(d, i) {
          d3.select(this).attr('fill', 'red');
        }

        function mouseOut(d, i) {

        }
        simulation.nodes(countryWiseListing).on('tick', updateNodes); // just for updating cx and cy randomly with every second.

        function updateNodes() {
          circles.attr('cx', function(d) {
              return d.x;
            })
            .attr('cy', function(d) {
              return d.y;
            });

          text.attr('x', function(d) {
              return d.x;
            })
            .attr('y', function(d) {
              return d.y
            });
        }

        var text = g.append("text")
          .attr("dy", ".2em")
          .style("text-anchor", "middle")
          .attr("font-family", "Gill Sans", "Gill Sans MT")
          .text(function(d) {
            return d.country;
          })
          .style("font-size", "0.6rem")
          .attr("fill", "black");
        circles.exit().remove();
      }

    }
  ])

})();
