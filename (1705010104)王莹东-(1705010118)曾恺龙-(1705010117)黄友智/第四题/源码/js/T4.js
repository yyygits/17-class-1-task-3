new Vue({
	el: '#app',
	data: {
		arr: null,
		newarr: null
	},
	computed: {
		newArr() {
			return this.newarr;
		},
	},
	methods: {
		start() {
			this.arr = JSON.parse(JSON.stringify(teachers));
		},
		generate() {
			if (this.arr) {
				let xx = JSON.parse(JSON.stringify(this.arr));
				// console.log(teachers)
				// return;
				for (i = 0; i < xx.length; i++) {
					if (xx[i].salary < 26000) {
						let temp = xx[i].salary + this.yearSum(xx[i].dateStart, xx[i].dateEnd) * 50 + xx[i].familyNum * 100
						if (temp >= 26000) {
							xx[i].salary = 26000
						} else {
							xx[i].salary = temp;
						}
					}

					this.newarr = xx
				}
			}
		},
		stringToDate(dateStr) {
			// dateStr = "2019-06-30";
			separator = "-";
			var dateArr = dateStr.split(separator);
			var year = parseInt(dateArr[0]);
			var month;
			//处理月份为04这样的情况                          
			if (dateArr[1].indexOf("0") == 0) {
				month = parseInt(dateArr[1].substring(1));
			} else {
				month = parseInt(dateArr[1]);
			}
			var day = parseInt(dateArr[2]);
			var date = new Date(year, month - 1, day).getTime();
			return date; //返回Date
		},
		yearSum(s, e) {
			start = this.stringToDate(s);
			end = this.stringToDate(e)
			sum = Math.floor((end - start) / (24 * 3600 * 1000 * 365))
			return sum;
		}
	}
})
