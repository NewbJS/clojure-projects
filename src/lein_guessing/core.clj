(ns lein-guessing.core)

(defn -main
  []
  (def gotWrong 0)
  (def ranNum (rand-int 11))
  (def won false)
  (println "Guess a random number, from 0 to to 10!")

  (while (not won)
    (def guess (Integer/parseInt (read-line)))

    (cond
      (< guess ranNum) (do
                         (println "That is incorrect! Guess higher!")
                         (def gotWrong (+ gotWrong 1)))
      (> guess ranNum) (do
                         (println "That is incorrect! Guess lower!")
                         (def gotWrong (+ gotWrong 1)))
      :else (do
              (if (> gotWrong 3)
                (println "You guessed correctly! You got" gotWrong "wrong. You lose.")
                (println "You guessed correctly! You got" gotWrong "wrong. You win."))
              (def won true))))

  (println "Would you like to play again?")
  (defn play-again
    []
    (def a (read-line))
    (cond
      (= a "y") (-main)
      (= a "n") nil
      :else (do
              (println "That is not a valid answer.")
              (play-again))))
  (play-again))