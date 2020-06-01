pub trait MulSym {
    fn mul(r1: Self, r2: Self) -> Self;
}

impl MulSym for i128 {
    fn mul(r1: Self, r2: Self) -> Self { r1 * r2 }
}

impl MulSym for String {
    fn mul(r1: Self, r2: Self) -> Self {format!( "({} * {})", r1, r2) }
}

