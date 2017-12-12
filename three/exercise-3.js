
const Rx = require('rxjs/Rx');

const number$ = Rx.Observable.range(0, 289326 + 1).skip(1);

number$
   .scan((acc, curr) => curr)
   .subscribe(console.log);

function checkMoveDirection(a, b) {
	return -1;
}