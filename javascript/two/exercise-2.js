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
            return Rx.Observable.from(intArray);
        }
        );

    /* Silver */
    const output$ = file$
        .mergeMap((row$) => row$.max().zip(row$.min(), (max, min) => max - min))
        .reduce((a, b) => a + b);

    output$.subscribe(console.log);

    /* Gold */
    var rowArray = splitted;
    var rowArrayLength = rowArray.length;
    var totalScore = 0;
    for (var i = 0; i < rowArrayLength; i++) {
        const numberArray = rowArray[i].split(" ");
        let index = 0;
        let foundMatch = false;
        var numberArrayLength = numberArray.length;
        while (!foundMatch) {
            for (var j = 0; j < numberArrayLength; j++) {
                if (index !== j && numberArray[index] % numberArray[j] === 0) {
                    totalScore += (numberArray[index] / numberArray[j])
                    foundMatch = true;
                }
            }
            index++;
        }
    }
    console.log(totalScore);

});