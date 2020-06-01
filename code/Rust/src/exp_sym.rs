pub trait ExpSym {
    fn lit(i: u64) -> Self;
    fn neg(r: Self) -> Self;
    fn add(r1: Self, r2: Self) -> Self;
}

impl ExpSym for i128 {
    fn lit(i: u64) -> Self { i as i128 }
    fn neg(r: Self) -> Self { -r }
    fn add(r1: Self, r2: Self) -> Self { r1 + r2 }
}

impl ExpSym for String {
    fn lit(i: u64) -> Self { format!("{}", i) }
    fn neg(r: Self) -> Self { format!("(-{})", r) }
    fn add(r1: Self, r2: Self) -> Self { format!("({} + {})", r1, r2) }
}