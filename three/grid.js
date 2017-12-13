module.exports = class Grid {
    
        constructor(x, y, grid) {
            this.x = x || 0;
            this.y = y || 0;
            this.grid = grid || new Map().set(0, new Map().set(0, true));
            this.grid.set(this.x, new Map().set(this.y, true));
        }

        left() {
            return this.grid.has(parseInt(this.x - 1)) && this.grid.get(parseInt(this.x - 1)).has(parseInt(this.y));
        }

        right() {
            return this.grid.has(parseInt(this.x + 1)) && this.grid.get(parseInt(this.x + 1)).has(parseInt(this.y));
        }

        up() {
            return this.grid.has(parseInt(this.x)) && this.grid.get(parseInt(this.x)).has(parseInt(this.y - 1));
        }

        down() {
            return this.grid.has(parseInt(this.x)) && this.grid.get(parseInt(this.x)).has(parseInt(this.y + 1));
        }
    
        moveLeft() {
            console.log('*left*');
            return new Grid(parseInt(this.x - 1), parseInt(this.y), this.grid);
        }

        moveRight() {
            console.log('*right*');
            return new Grid(parseInt(this.x + 1), parseInt(this.y), this.grid);
        }

        moveUp() {
            console.log('*up*');
            return new Grid(parseInt(this.x), parseInt(this.y - 1), this.grid);
        }

        moveDown() {
            console.log('*down*');
            return new Grid(parseInt(this.x), parseInt(this.y + 1), this.grid);
        }

        getX() {
            return this.x;
        }

        getY() {
            return this.y;
        }

    }
    