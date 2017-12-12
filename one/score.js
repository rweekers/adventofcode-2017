module.exports = class Score {
    
        constructor(last, sum) {
            this.last = last;
            this.sum = sum;
        }
    
        add(value) {
            this.sum = this.sum + value;
            return this;
        }

        setLast(value) {
            this.last = value;
            return this;
        }
    
        getLast() {
            return this.last;
        }
    
        getSum() {
            return this.sum;
        }
    }
    