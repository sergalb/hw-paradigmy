#include <iostream>
#include <vector>
#include <limits>
#include "big_integer.h"
#include "big_integer1.h"

using namespace std;
int main() {

    big_integer a("58116113662464");
    //a = big_integer("-817481237412378461284761285761238721364871236412387461238476");
    big_integer b("-69915340275335725947039967344780301516941178763832166830074976531586637913875719092943773294819887361305827990306816");
    big_integer t("30513334078941246734760927244636687711861439493366720162126683127340916142378508481529507039934971720499200000000000");
    a /= b;
    cout<< a<<endl;

return 0;
}


/*

big_integer &big_integer::operator*=(big_integer const &arg) {
    big_integer first = abs(*this), second = abs(arg);
    int sign_result = sign(*this) * sign(arg);
    big_integer result;
    for (int j = 0; j < second.number.size(); j++) {
        unsigned long long m = 0;
        big_integer tmp_bi;
        tmp_bi.number.resize(first.number.size() + j + 1, 0);
        for (int i = 0; i < first.number.size(); i++){
            auto sm = (unsigned long long)((unsigned)first.number[i]);
            sm *= (unsigned long long)((unsigned)second.number[j]);
            sm += m;
            tmp_bi.number[i + j] = static_cast<int>(sm);
            sm >>= 32;
            m = sm;
        }
        tmp_bi.optimize();
        tmp_bi.number[first.number.size() + j] += static_castint>(m);
        result += tmp_bi;
    }
    *this = (sign_result == -1) ? -result : result;
    return *this;
}*/
