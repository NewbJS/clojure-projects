(ns lein-guessing.core)


(declare play-again)
(declare try-parse)
(declare handle-hint)

(defn -main
  []
  (println "Do you want to enable hints?[y/n]")
  (let [ran-num (rand-int 11) wants-hints (read-line)]
    (println "Guess a random number, from 0 to to 10!")
    (loop [got-wrong 0]
      (let [guess (try-parse)]
        (cond
          (< guess ran-num) (do
                              (println "That is incorrect! Guess higher!")
                              (if (= wants-hints "y")
                                (handle-hint ran-num true))
                              (recur (inc got-wrong)))
          (> guess ran-num) (do
                              (println "That is incorrect! Guess lower!")
                              (if (= wants-hints "y")
                                (handle-hint ran-num true))
                              (recur (inc got-wrong)))
          :else (do
                  (if (> got-wrong 3)
                    (println "You guessed correctly! You got" got-wrong "wrong. You lose.")
                    (println "You guessed correctly! You got" got-wrong "wrong. You win!")))))))
  (play-again))


(defn handle-hint
  [number wants-hints]
  (if (= wants-hints true) (do
                                     (println "Do you want a hint?[y/n]")
                                     (let [min (- number 2) max (+ number 2) answer (read-line)]
                                       (if (= answer "y") (do
                                                            (loop [narrow-num (rand-int 11)]
                                                              (if (or (< narrow-num min) (> narrow-num max))
                                                                (recur (rand-int 11))
                                                                (println "The random number is at least" (- narrow-num 2) "and at most" (+ narrow-num 2) "."))))
                                                          (println "proceeding..."))))
                                   nil))

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
