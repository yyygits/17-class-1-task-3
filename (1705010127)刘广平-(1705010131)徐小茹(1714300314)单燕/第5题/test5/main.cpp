#include <stdio.h>
#include <iostream>
#include <ctime>
#include <algorithm>
using namespace std;
typedef long long ll;
const int cardnum = 52;
const int si = 3, inf = 1e7;
int a[si], b[si];

void showcard(int x) {
    int cc = (x - 1 ) / 13;
    int vv = (x - 1 ) % 13 + 1;

    if (cc == 0) printf(" 黑桃");
    else if (cc == 1) printf(" 红心");
    else if (cc == 2) printf(" 梅花");
    else printf(" 方块");

    if (vv == 1) printf("A ");
    else if (vv == 11) printf("J ");
    else if (vv == 12) printf("Q ");
    else if (vv == 13) printf("K ");
    else printf("%d ", vv);
}
void generate() {
    //一副牌只有52张 且不能重复 用位运算 排除重复 速度很快
    ll sta = 0;//int只有32位 装不下52个牌 要用ll
    srand( (unsigned)time( NULL));
    for (int i = 0; i < si; i++) {
        do {
            a[i] = rand() % cardnum + 1;
        } while (sta >> a[i] & 1);
        sta |= 1 << a[i];
    }
    for (int i = 0; i < si; i++) {
        do {
            b[i] = rand() % cardnum + 1;
        } while (sta >> b[i] & 1);
        sta |= 1 << b[i];
    }
    //这样6个牌都是不重复的

    cout << endl << "a : "  ;
    for (int i = 0; i < si; i++) showcard(a[i]);
    cout <<"\n"<<endl;
    cout << endl << "b : "  ;
    for (int i = 0; i < si; i++) showcard(b[i]);
    cout << endl;
    cout <<"\n"<<endl;
}

void cal(int &g, int &v, int arr[]) {
    int color[si], val[si];//花色值
    int sum = 0;
    for (int i = 0; i < si; i++)  {
        color[i] = (arr[i] - 1 ) / 13; //设一张牌的编号是 x 则(x - 1)/13是他的花色 即分别是0 1 2 3
        val[i] = (arr[i] - 1 ) % 13 + 1; //(x - 1) % 13  + 1是他的值
        sum += val[i];
    }
    sort(val, val + si);//排序方便比较
    sort(color, color + si);



    int fl = 1;//同花
    for (int i = 0; i < si - 1; i++)  if (color[i] != color[i + 1]) fl = 0;
    if (fl) {
        if (val[0] == 1 && val[1] == 12 && val[2] == 13) v = inf;
        else v = sum;
        g = 0;
        cout << " 同花\n" << endl;
        return ;
    }
    fl = 1;//顺子 因为前面已经判断了 花色是不同的 所以不用判断了
    for (int i = 0; i < si - 1; i++)  if (val[i] + 1 != val[i + 1]) fl = 0;
    if (fl) {
        if (val[0] == 1 && val[1] == 12 && val[2] == 13) v = inf;
        else v = sum;
        g = 1;
        cout <<  " 顺子\n" << endl;
        return ;
    }
    fl = 1;//同点
    for (int i = 0; i < si - 1; i++)  if (val[i] != val[i + 1]) fl = 0;
    if (fl) {
        if (sum == 3) v = inf;
        else v = sum;
        g = 2;
        cout << " 同点\n" << endl;
        return ;
    }
    fl = 0;//对子
    for (int i = 0; i < si - 1; i++)  if (val[i] == val[i + 1]) fl = 1;
    if (fl) {
        if (val[0] == 1 && val[1] == 1 && val[2] == 13) v = inf;
        else v = sum;
        g = 3;
        cout << " 对子\n" << endl;
        return ;
    }

    //杂牌
    cout << " 杂牌\n" << endl;
    if (val[0] == 1 && val[1] == 11 && val[2] == 13) v = inf;
    else v = sum;
    g = 4;
    return ;
}
int comparee() {//比较
    int ag, av, bg, bv;// a等级和点数 b的等级和点数
    cal(ag, av, a);//计算a b的等级和点数 0是最高 同花 最弱是4
    cal(bg, bv, b);

    printf("a点数 : %d,   b点数 : %d\n\n", av, bv);

    if (ag < bg)    return 1;
    if (ag > bg)    return -1;
    if (av > bv)    return 1;
    if (av < bv)    return -1;
    return 0;
}
int main() {
    printf("请按回车键开始发牌......");
    getchar();
    //对它进行编码 方便逻辑实现
    //按照1 2 3...J Q K 分
    //1-13黑桃 14-26红心 27-39梅花 40-52方块
    // 0        1           2        3
    //设一张牌的编号是 x 则(x - 1)/13是他的花色 即分别是0 1 2 3
    //(x - 1) % 13  + 1是他的值
    //inf  表示这种等级中最大的等级
    generate();//生成随机数
    int s = comparee();//比较

    if (s > 0) printf("a胜利 \n");
    if (s < 0) printf("b胜利 \n");
    if (s == 0) printf("平局 \n");
    return 0;

}
