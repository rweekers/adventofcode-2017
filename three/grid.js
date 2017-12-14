module.exports = class Grid {
    
        constructor(x, y, grid) {
            this.x = x || 0;
            this.y = y || 0;
            this.grid = grid || new Map().set(0, new Map().set(0, true));
            this.updateGrid();
        }

        updateGrid() {
            if (this.grid.has(this.x)) {
                this.grid.get(this.x).set(this.y, true);
            } else {
                this.grid.set(this.x, new Map().set(this.y, true));
            }
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
            return new Grid(parseInt(this.x - 1), parseInt(this.y), this.grid);
        }

        moveRight() {
            return new Grid(parseInt(this.x + 1), parseInt(this.y), this.grid);
        }

        moveUp() {
            return new Grid(parseInt(this.x), parseInt(this.y - 1), this.grid);
        }

        moveDown() {
            return new Grid(parseInt(this.x), parseInt(this.y + 1), this.grid);
        }

        getX() {
            return this.x;
        }

        getY() {
            return this.y;
        }

    }
    