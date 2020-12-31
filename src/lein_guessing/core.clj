(ns lein-guessing.core)


(declare play-again)
(declare try-parse)

(defn -main
  []
  (let [ranNum (rand-int 11)]
  (println "Guess a random number, from 0 to to 10!")

  (loop [got-wrong 0]
    (let [guess (try-parse)]
               (cond
                 (< guess ranNum) (do
                                    (println "That is incorrect! Guess higher!")
                                    (recur (inc got-wrong)))
                 (> guess ranNum) (do
                                    (println "That is incorrect! Guess lower!")
                                    (recur (inc got-wrong)))
                 :else (do
                         (if (> got-wrong 3)
                           (println "You guessed correctly! You got" got-wrong "wrong. You lose.")
                           (println "You guessed correctly! You got" got-wrong "wrong. You win.")))))))
  (play-again))


(defn play-again
  []
  (println "Would you like to play again?[y/n]")
  (let [a (read-line)]
    (cond
      (= a "y") (-main)
      (= a "n") nil
      :else (do
              (println "That is not a valid answer.")
              (play-again)))))


(defn try-parse
  []
  (let [x (read-line)]
    (try
      (Integer/parseInt x)
      (catch NumberFormatException _ (do
                                       (println "That is not a number!")
                                       (try-parse))))))