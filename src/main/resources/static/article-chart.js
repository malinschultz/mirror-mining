var myChartObject = document.getElementById('myChart');

var chart = new Chart(myChartObject, {
    type: 'horizontalBar',
    data: {
        labels: ["Analytical", "Anger", "Confident", "Fear", "Joy", "Sadness", "Tentative"],
        datasets: [{
            label: "average tone Kategorie",
            backgroundColor: 'rgba(255,0,0,0.4)',
            borderColor: 'rgba(255,0,0,1)',
            data: [0.781239 , 0, 0.702673, 0.583166, 0.587752, 0.581369, 0.760855]
        },{
            label: "average tone all categories",
            fill: true,
            backgroundColor: 'rgba(32,189,57,0.48)',
            borderColor: 'rgba(32,189,57,1)',
            data: [0.53495, 0.89323, 0.69058, 0.54758, 0.31234, 0.781923, 0.82012]
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }]
        }
    }
})