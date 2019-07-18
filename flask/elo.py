from db import updatePlayer, getPlyrPoints

ELO_K = 70

def calc_prob(elo1, elo2):
    return 1.0 / (1.0 + pow(10, (elo1-elo2)/400))

def calc_new(w_elo, l_elo):
    p1 = calc_prob(l_elo, w_elo)
    p2 = calc_prob(w_elo, l_elo)

    print("Winner " + str(w_elo) + " had " + str(p1) + " chance of winning")
    print("Loser " + str(l_elo) + " had " + str(p2) + " chance of winning")

    new_win = w_elo + ELO_K * (1 - p1)
    new_lsr = l_elo + ELO_K * (0 - p2)

    return (new_win, new_lsr)

def eval_match(winner, loser, game): #takes winnerId LoserID and gameID
    win_elo = getPlyrPoints(str(winner), str(game))
    print(str(win_elo))
    lsr_elo = getPlyrPoints(str(loser), str(game))
    print(str(lsr_elo))

    if (win_elo==None or lsr_elo==None):
        print("Someone's first game!!!")

    new_points = calc_new(win_elo, lsr_elo)
    print("winner new points: " + str(new_points[0]))
    print("loser new points: " + str(new_points[1]))
    updatePlayer(str(winner), str(game), str(new_points[0]))
    updatePlayer(str(loser), str(game), str(new_points[1]))
    return None
