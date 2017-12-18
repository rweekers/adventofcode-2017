const Rx = require('rxjs/Rx');
const Grid = require('./grid');

/* Silver */

const steps2 = 289326;
const steps = 7;
const number$ = Rx.Observable.range(0, steps).skip(1);

// number$
//    .reduce((acc, curr) => doMove(acc), new Grid())
//    .map(grid => Math.abs(grid.getX()) + Math.abs(grid.getY()))
//    .subscribe(console.log);

/* Gold */
number$
    .scan((acc, curr) => doMove(acc), new Grid())
    .map(grid => grid.getLastScore())
    .subscribe(x => console.log('emit ' + x));


function doMove(grid) {
    // default movement is to the right, always try to turn left

    // initial movement
    if (grid.getX() === 0 && grid.getY() === 0) {
        return grid.moveRight();
    }

    // algorithm for moving
    if (grid.right() == true) {
        // right is occupied, go downwards
        if(grid.down() == true) {
            return grid.moveLeft();
        } else {
            return grid.moveDown();
        }
    } else {
        // right is free, first try upwards and then leftwards
        if(grid.up() == true) {
            // up is occupied, go right
            return grid.moveRight();
        } else {
            if (grid.left() == true) {
                // go down, right and up are both occupied
                return grid.moveUp();
            } else {
                return grid.moveLeft();
            }
        }
    }
}