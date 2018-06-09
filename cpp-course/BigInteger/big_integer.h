#ifndef BIG_INTEGER_H
#define BIG_INTEGER_H

#include <cstddef>
//#include <gmp.h>
#include <iosfwd>
#include <stdint-gcc.h>
#include <vector>
#include <string>

struct big_integer {

    big_integer();

    big_integer(big_integer const &other);

    big_integer(int a);

    explicit big_integer(std::string str);

    ~big_integer();

    big_integer &operator=(big_integer const &other);

    big_integer &operator+=(big_integer const &rhs);

    big_integer &operator-=(big_integer const &rhs);

    big_integer &operator*=(big_integer const &rhs);

    big_integer &operator/=(big_integer const &rhs);

    big_integer &operator%=(big_integer const &rhs);

    big_integer &operator&=(big_integer const &rhs);

    big_integer &operator|=(big_integer const &rhs);

    big_integer &operator^=(big_integer const &rhs);

    big_integer &operator<<=(unsigned rhs);

    big_integer &operator>>=(unsigned rhs);

    big_integer operator+() const;

    big_integer operator-() const;

    big_integer operator~() const;

    big_integer &operator++();

    big_integer operator++(int);

    big_integer &operator--();

    big_integer operator--(int);

    friend bool operator==(big_integer const &a, big_integer const &b);

    friend bool operator!=(big_integer const &a, big_integer const &b);

    friend bool operator<(big_integer const &a, big_integer const &b);

    friend bool operator>(big_integer const &a, big_integer const &b);

    friend bool operator<=(big_integer const &a, big_integer const &b);

    friend bool operator>=(big_integer const &a, big_integer const &b);

    friend std::string to_string(big_integer const &b_i);

    friend void del_useless_zero(big_integer &b_i);

    friend big_integer div_m_in_n(big_integer dividend, big_integer divider);

    friend big_integer div_m_in_1(big_integer dividend, uint32_t divider, uint32_t sign);

    friend unsigned int normalize(big_integer &dividend, big_integer &divider);

    friend uint32_t normalize(big_integer &dividend, uint32_t &divider);

    friend void extend(big_integer &b_i, uint64_t to_size);

    friend big_integer abs(big_integer &arg);

    friend big_integer &
    bit_operation(big_integer &a, big_integer const &b, void (*operation)(uint32_t &a, uint32_t const b));

private:
    std::vector<uint32_t> vec;
};

big_integer operator+(big_integer a, big_integer const &b);

big_integer operator-(big_integer a, big_integer const &b);

big_integer operator*(big_integer a, big_integer const &b);

big_integer operator/(big_integer a, big_integer const &b);

big_integer operator%(big_integer a, big_integer const &b);

big_integer operator&(big_integer a, big_integer const &b);

big_integer operator|(big_integer a, big_integer const &b);

big_integer operator^(big_integer a, big_integer const &b);

big_integer operator&(big_integer a, uint32_t b);

big_integer operator|(big_integer a, uint32_t b);

big_integer operator^(big_integer a, uint32_t b);

big_integer operator<<(big_integer a, unsigned int b);

big_integer operator>>(big_integer a, unsigned int b);

bool operator==(big_integer const &a, big_integer const &b);

bool operator!=(big_integer const &a, big_integer const &b);

bool operator<(big_integer const &a, big_integer const &b);

bool operator>(big_integer const &a, big_integer const &b);

bool operator<=(big_integer const &a, big_integer const &b);

bool operator>=(big_integer const &a, big_integer const &b);

std::string to_string(big_integer const &b_i);

std::ostream &operator<<(std::ostream &s, big_integer const &a);

void multiply_string(std::string &str, int carry);

void divide_string(std::string &str);

uint32_t right_half(uint64_t arg);

uint32_t left_half(uint64_t arg);

big_integer div_m_in_n(big_integer dividend, big_integer divider);

uint32_t div_3_in_2(uint32_t dividend1, uint32_t dividend2, uint32_t dividend3, uint32_t divider1,
                    uint32_t divider2);

big_integer div_m_in_1(big_integer dividend, uint32_t divider, uint32_t sign);

std::pair<uint32_t, uint32_t>
div_2_in_1(uint32_t dividend1, uint32_t dividend2, uint32_t divider);

uint64_t link(uint32_t first, uint32_t second);

uint32_t inline normalize(big_integer &dividend, big_integer &divider);

uint32_t inline normalize(big_integer &dividend, uint32_t &divider);

void inline extend(big_integer &b_i, uint64_t to_size);

big_integer &bit_operation(big_integer &a, big_integer const &b, void (*operation)(uint32_t &a, uint32_t const b));

void or_(uint32_t &a, uint32_t const b);

void and_(uint32_t &a, uint32_t const b);

void xor_(uint32_t &a, uint32_t const b);

#endif // BIG_INTEGER_H


