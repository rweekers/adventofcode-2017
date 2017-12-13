const Rx = require('rxjs/Rx');
const Grid = require('./grid');

const number$ = Rx.Observable.range(0, 289326 + 1).skip(1);

number$
   .scan((acc, curr) => doMove(acc), new Grid()).take(4)
   .map(grid => grid.getX() + ', ' + grid.getY())
   .subscribe(console.log);

function doMove(grid) {
    // default movement is to the right, always try to turn left
    console.log('right: ' + grid.right() + ' left: ' + grid.left() + ' up: ' + grid.up() + ' down: ' + grid.down());


    // initial movement
    if (grid.getX() === 0 && grid.getY() === 0) {
        return grid.moveRight();
    }

    // algorithm for moving
    if (grid.right() == true) {
        // right is occupied, go downwards
        return grid.moveDown();
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