const Rx = require('rxjs/Rx');
const fs = require('fs');

fs.readFile('input.txt', 'utf8', function (err, data) {
    if (err) {
        return console.log(err);
    }
    const splitted = data.split("\n");

    const file$ = Rx.Observable.from(splitted)
        .map(row => {
            var intArray = row.split(" ");
            return Rx.Observable.from(intArray);
        }
        );

    /* Silver */
    const output$ = file$
        .mergeMap(word$ => word$);

    const distinct$ = output$.distinct();

    const result$ = output$
                        .zip(distinct$, (a, b) => a === b);
                        //.scan((acc, curr) => acc === false ? 'invalid' : 'valid');

    result$.subscribe(console.log);

});