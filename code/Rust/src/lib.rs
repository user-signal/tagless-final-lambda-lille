pub mod exp_sym;
pub mod mul_sym;

use crate::exp_sym::ExpSym;
use crate::mul_sym::MulSym;

pub fn tf1<T: ExpSym>() -> T {
    ExpSym::add(
        ExpSym::num(8),
        ExpSym::neg(
            ExpSym::add(ExpSym::num(1), ExpSym::num(2))))
}

pub fn tf2<T: ExpSym + MulSym>() -> T { MulSym::mul(ExpSym::num(7), tf1()) }

pub fn eval(e: i128) -> i128 { e }

pub fn view(e: String) -> String { e }

#[test]
fn test_eval_tf1() {
    assert_eq!(eval(tf1()), 5)
}

#[test]
fn test_view_tf1() {
    assert_eq!(view(tf1()), "(8 + (-(1 + 2)))")
}

#[test]
fn test_eval_tfm1() {
    assert_eq!(eval(tf2()), 35)
}

#[test]
fn test_view_tfm2() {
    assert_eq!(view(tf2()), "(7 * (8 + (-(1 + 2))))")
}
