pub mod exp_sym;
pub mod mul_sym;

use crate::exp_sym::ExpSym;
use crate::mul_sym::MulSym;

pub fn tf1<T: ExpSym>() -> T { ExpSym::add(ExpSym::lit(8), ExpSym::neg(ExpSym::add(ExpSym::lit(1), ExpSym::lit(2)))) }
pub fn tfm1<T: ExpSym + MulSym>() -> T { ExpSym::add(ExpSym::lit(7), ExpSym::neg(MulSym::mul(ExpSym::lit(2), ExpSym::lit(1)))) }
pub fn tfm2<T: ExpSym + MulSym>() -> T { MulSym::mul(ExpSym::lit(7), tf1()) }

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
    assert_eq!(eval(tfm1()), 5)
}

#[test]
fn test_view_tfm1() {
    assert_eq!(view(tfm1()), "(7 + (-(2 * 1)))")
}

#[test]
fn test_eval_tfm2() {
    assert_eq!(eval(tfm2()), 35)
}

#[test]
fn test_view_tfm2() {
    assert_eq!(view(tfm2()), "(7 * (8 + (-(1 + 2))))")
}
