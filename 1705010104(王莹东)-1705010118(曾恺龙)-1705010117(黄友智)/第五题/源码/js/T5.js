new Vue({
	el: '#app',
	data: {
		Rsum: 0,
		Bsum: 0,
		startFlag: false,
		dialog: false,
		p1_name: '红色方',
		p2_name: '蓝色方',
		round: 0,
		Rcount: 0,
		Bcount: 0,
		Rtype: '',
		Btype: '',
		winner: '',
		Rtonghua: 0,
		Btonghua: 0,
		Rduizi: 0,
		Bduizi: 0,
		Rtongdian: 0,
		Btongdian: 0,
		Rzapai: 0,
		Bzapai: 0,
		Bshunzi: 0,
		Rshunzi: 0,
		n1: null,
		n2: null,
		n3: null,
		n4: null,
		n5: null,
		n6: null,
		cards: puke,
		p1: [],
		p2: []
	},
	computed: {

		player1(value) {
			console.log(this.startFlag)
			if (!this.startFlag) return null;
			let flag = this.round
			this.n1 = Math.floor(Math.random() * 52);
			this.n2 = Math.floor(Math.random() * 52);
			this.n3 = Math.floor(Math.random() * 52);
			while (this.n1 == this.n2 || this.n1 == this.n3 || this.n2 == this.n3) {
				this.n1 = Math.floor(Math.random() * 52);
				this.n2 = Math.floor(Math.random() * 52);
				this.n3 = Math.floor(Math.random() * 52);
			}
			this.p1 = [{
					content: this.cards[this.n1].content,
					color: this.cards[this.n1].color,
					number: this.cards[this.n1].number
				},
				{
					content: this.cards[this.n2].content,
					color: this.cards[this.n2].color,
					number: this.cards[this.n2].number
				},
				{
					content: this.cards[this.n3].content,
					color: this.cards[this.n3].color,
					number: this.cards[this.n3].number
				},
			]
			return this.p1;
		},
		player2() {
			if (!this.startFlag) return null;
			let flag = this.round
			this.n4 = Math.floor(Math.random() * 52);
			this.n5 = Math.floor(Math.random() * 52);
			this.n6 = Math.floor(Math.random() * 52);
			while (this.n4 == this.n5 || this.n4 == this.n6 || this.n5 == this.n6 || this.n4 == this.n1 || this.n4 == this.n2 ||
				this.n4 == this.n3 ||
				this.n5 == this.n1 || this.n5 == this.n2 || this.n5 == this.n3 || this.n6 == this.n1 || this.n6 == this.n2 ||
				this.n6 == this.n3) {
				this.n4 = Math.floor(Math.random() * 52);
				this.n5 = Math.floor(Math.random() * 52);
				this.n6 = Math.floor(Math.random() * 52);
			}
			this.p2 = [{
					content: this.cards[this.n4].content,
					color: this.cards[this.n4].color,
					number: this.cards[this.n4].number
				},
				{
					content: this.cards[this.n5].content,
					color: this.cards[this.n5].color,
					number: this.cards[this.n5].number
				},
				{
					content: this.cards[this.n6].content,
					color: this.cards[this.n6].color,
					number: this.cards[this.n6].number
				},
			]
			return this.p2;
		},
		getRcount() {
			if (!this.startFlag) return;
			let sum = 0;
			arr = new Array()
			for (i = 0; i < 3; i++) {
				if (this.p1[i].number == 'J') {
					sum += 11
				} else if (this.p1[i].number == 'Q') {
					sum += 12
				} else if (this.p1[i].number == 'K') {
					sum += 13
				} else if (this.p1[i].number == 'A') {
					sum += 14
				} else {
					sum += Number(this.p1[i].number)
				}
			}
			sum += this.extraSum(this.p1)
			switch (Math.floor(sum / 100)) {
				case 1:
					this.Rtype = '对子';
					break;
				case 2:
					this.Rtype = '顺子';
					break;
				case 3:
					this.Rtype = '同点';
					break;
				case 4:
					this.Rtype = '同花';
					break;
				default:
					this.Rtype = '杂牌';
					break;
			}
			this.Rsum = sum
			return this.Rsum;
		},
		getBcount() {
			if (!this.startFlag) return;
			let sum = 0;
			arr = new Array()
			if (this.p2.length > 0) {
				for (i = 0; i < 3; i++) {
					if (this.p2[i].number == 'J') {
						sum += 11
					} else if (this.p2[i].number == 'Q') {
						sum += 12
					} else if (this.p2[i].number == 'K') {
						sum += 13
					} else if (this.p2[i].number == 'A') {
						sum += 14
					} else {
						sum += Number(this.p2[i].number)
					}
				}
				sum += this.extraSum(this.p2)
				switch (Math.floor(sum / 100)) {
					case 1:
						this.Btype = '对子';
						break;
					case 2:
						this.Btype = '顺子';
						break;
					case 3:
						this.Btype = '同点';
						break;
					case 4:
						this.Btype = '同花';
						break;
					default:
						this.Btype = '杂牌'
						break;
				}
				this.Bsum = sum
				return this.Bsum;
			}
			this.Bsum = sum
			return this.Bsum;
		},
		getRtype() {
			return this.Rtype;
		},
		getBtype() {

			return this.Btype;
		},
		getTHsum() {
			return this.Btonghua + this.Rtonghua;
		},
		getTDsum() {
			return this.Btongdian + this.Rtongdian;
		},
		getSZsum() {
			return this.Bshunzi + this.Rshunzi;
		},
		getDZsum() {
			return this.Bduizi + this.Rduizi;
		},
		getZPsum() {
			return this.Bzapai + this.Rzapai;
		},
	},
	methods: {
		start() {
			this.Rcount = 0;
			this.dialog = false;
			this.round++;
			this.startFlag = true;
			this.compute()
		},
		compute(){
			if (this.Rsum >this.Bsum) {
				switch (this.Rtype) {
					case '同花':console.log("同花加1")
						this.Rtonghua++;
						break;
					case '同点':console.log("同点加1")
						this.Rtongdian++;
						break;
					case '对子':console.log("对子加1")
						this.Rduizi++;
						break;
					case '顺子':console.log("顺子加1")
						this.Rshunzi++;
						break;
					default:console.log("杂牌加1")
						this.Rzapai++;
						break;
				}
			} else if (this.Rsum < this.Bsum) {
				switch (this.Btype) {
					case '同花':
						this.Btonghua++;
						break;
					case '同点':
						this.Btongdian++;
						break;
					case '对子':
						this.Bduizi++;
						break;
					case '顺子':
						this.Bshunzi++;
						break;
					default:
						this.Bzapai++;
						break;
				}
			}
		},
		close() {
			if (confirm("确定结束游戏？")) {
				this.compute()
				this.dialog = true;
			}
			// alert("本次游戏")
		},
		extraSum(x) {
			let sum = 0
			let arr = new Array()
			reg = /^(2(?=3)|3(?=4)|4(?=5)|5(?=6)|6(?=7)|7(?=8)|8(?=9)|9(?=10)|10(?=11)|11(?=12)|12(?=13)|13(?=14)){2}\d$/;
			if (x[0].content == x[1].content && x[0].content == x[2].content) { //同花
				sum = 400;
				return sum;
			}
			if (x[0].number == x[1].number && x[0].number == x[2].number) { //同点
				sum = 200;
				return sum;
			}
			if (this.Shunzi(x)) { //顺子
				sum = 300;
				return sum;
			}
			if (this.Duizi(x)) {
				sum = 100;
				return sum;
			}
			return 0;
		},
		sortNumber(a, b) {
			return a - b;
		},
		Shunzi(x) {
			let k = 0;
			for (i = 0; i < 3; i++) {
				if (x[i].number == 'J') {
					arr[k++] = 11
				} else if (x[i].number == 'Q') {
					arr[k++] = 12
				} else if (x[i].number == 'K') {
					arr[k++] = 13
				} else if (x[i].number == 'A') {
					arr[k++] = 14
				} else {
					arr[k++] = Number(x[i].number)
				}
			}
			console.log(arr.sort(this.sortNumber))
			if (arr[0] + 1 == arr[1] && arr[1] + 1 == arr[2]) {
				return true
			}
			return false;
		},
		Duizi(x) {
			for (i = 0; i < 2; i++) {
				for (j = i + 1; j < 3; j++) {
					if (x[i].number == x[j].number) {
						return true;
					}
				}
			}
			return false;
		},
		close_dialog() {
			this.dialog = !this.dialog,
				this.startFlag = false,
				this.Rtonghua = 0,
				this.Btonghua = 0,
				this.Rduizi = 0,
				this.Bduizi = 0,
				this.Rtongdian = 0,
				this.Btongdian = 0,
				this.Rzapai = 0,
				this.Bzapai = 0,
				this.Bshunzi = 0,
				this.Rshunzi = 0,
				location.reload();
		}
	}
})
