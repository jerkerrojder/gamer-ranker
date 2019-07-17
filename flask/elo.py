ELO_K = 70
res = {
    "win": 1,
    "loss": 0
}

def calc_prob(elo1, elo2):
    return 1.0 / (1.0 + pow(10, (elo1-elo2)/400))

def calc_new(elo1, elo2, is_win):
    p1 = calc_prob(elo1, elo2)
    p2 = 1 - p1

    res1 = 1 if is_win else 0
    res2 = 1 - res1

    elo1 = elo1 + ELO_K * (res1 – p1)
    elo2 = elo2 + ELO_K * (res2 – p2)
