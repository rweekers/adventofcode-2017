const Rx = require('rxjs/Rx');
const fs = require('fs');

fs.readFile('input.txt', 'utf8', function (err, data) {
   if (err) {
       return console.log(err);
   }
   const splitted = data.split("\n");

   const file$ = Rx.Observable.from(splitted)
       .map(row => {
            var intArray = row.split(" ").map(function (x) { 
                return parseInt(x, 10); 
            });
           return Rx.Observable.from(intArray);}
       );
       
   const output$ = file$
       .mergeMap((row$) => row$.max().zip(row$.min(), (max, min) => max - min))
       .reduce((a, b) => a + b);
   
   output$.subscribe(console.log);
});