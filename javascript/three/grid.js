module.exports = class Grid {
    
        constructor(x, y, grid) {
            this.x = x || 0;
            this.y = y || 0;
            this.grid = grid || new Map().set(0, new Map().set(0, 1));
            this.updateGrid();
        }

        updateGrid() {
            if (this.x == 0 && this.y == 0) {
                return;
            }
            this.lastScore = this.getAdjacentScores();
            if (this.grid.has(this.x)) {
                this.grid.get(this.x).set(this.y, this.lastScore);
            } else {
                this.grid.set(this.x, new Map().set(this.y, this.lastScore));
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

        getLastScore() {
            return this.lastScore;
        }

        getAdjacentScores() {
            let score = 0;
            let x = this.x - 1;
            let y = this.y - 1;
            const endX = this.x + 1;
            const endY = this.y + 1;
            while (x <= endX) {
                while (y <= endY) {
                    let fieldScore = this.getFieldScore(x, y);
                    if (x == this.x && y == this.y) {
                        fieldScore = 0;
                    }
                    score += fieldScore;
                    y++;
                }
                y = -1;
                x++;
            }
            return score;
        }

        getFieldScore(x, y) {
            if (this.grid === undefined) {
                return 0;
            }
            if (this.grid.has(x) && this.grid.get(x).has(y)) {
                return this.grid.get(x).get(y);
            }
            return 0;
        }

    }
    