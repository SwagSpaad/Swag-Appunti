# Problema 1
![[Pasted image 20250717114228.png|center|500]]

![[Pasted image 20250717114611.png|center|500]]

## a.) Valore minimo coefficiente di attrito statico $\mu_{s_{min}}$ 
Da ipotesi la cassa si trova in equilibrio sotto l'azione della forza peso $m \overrightarrow{g}$, della reazione vincolare $\overrightarrow{N}$ e della forza di attrito statico $\overrightarrow{F_{s}}$.

Dopo aver fissato gli assi, osserviamo le componenti delle forze $$\begin{align}&(m \overrightarrow{g})_{x}=mg \sin{\Theta}\qquad &&(m \overrightarrow{g})_{y}=-mg \cos{\Theta}\\\\
&N_{x}=0\qquad &&N_{y}=|\overrightarrow{N}|\\\\
&F_{sx}=-|\overrightarrow{F_{s}}|\qquad &&F_{sy}=0\end{align}$$
Per la condizione di equilibrio vale $$m \overrightarrow{g}+ \overrightarrow{N}+ \overrightarrow{F_{s}}=0$$ da cui otteniamo il sistema $$\begin{align}&\begin{cases}(m \overrightarrow{g})_{x}+N_{x}+F_{sx}=0\\\\ (m \overrightarrow{g})_{y}+N_{y}+F_{sy}=0\\\\\end{cases}\implies \begin{cases}mg \sin{\Theta}-|\overrightarrow{F_{s}}|=0\\\\-mg \cos{\Theta}+|\overrightarrow{N}|=0\end{cases}\\\\
&\begin{cases}|\overrightarrow{F_{s}}|=mg \sin{\Theta}\\\\|\overrightarrow{N}|=mg \cos{\Theta}\end{cases}\end{align}$$

Per la forza di attrito statico risulta $$|\overrightarrow{F_{s}}|\leq\mu_{s}\cdot|\overrightarrow{N}|\implies \cancel{mg} \sin{\Theta}\le \mu_{s}\cancel{mg} \cos{\Theta}$$
da cui otteniamo $$\mu_{s}\ge \frac{\sin{\Theta}}{\cos{\Theta}}=\tan\Theta$$
quindi il valore minimo del coefficiente di attrito statico deve valere $$\mu_{s_{min}}=\tan\Theta=\tan30°= \frac{1}{\sqrt{3}}\simeq0.57735$$
## b.) Modulo forza $F$ necessaria per spingere la cassa con accelerazione $1 \frac{m}{s^{2}}$ 
![[Pasted image 20250717120527.png|center|500]]

Dalla seconda legge della dinamica $$m \overrightarrow{a}=m \overrightarrow{g}+ \overrightarrow{N}+ \overrightarrow{F_{d}}+ \overrightarrow{F}$$
Tenendo conto che $a_{y}=0$ impostiamo il sistema nelle componenti cartesiane $$\begin{align}\begin{cases}(m \overrightarrow{g})_{x}+ N_{x}+ F_{x}+ F_{dx}=ma_{x}\\\\(m \overrightarrow{g})_{y}+ N_{y}+ F_{y}+ F_{dy}=0\end{cases}\end{align}$$
otteniamo le componenti $$\begin{align}&(m \overrightarrow{g})_{x}=-mg \sin{\Theta}\qquad &&(m \overrightarrow{g})_{y}=-mg \cos{\Theta}\\\\
&N_{x}=0\qquad &&N_{y}=|\overrightarrow{N}|\\\\
&F_{dx}=-\mu_{d}|\overrightarrow{N}|\qquad &&F_{dy}=0\\\\
&F_{x}=|\overrightarrow{F}|\qquad &&F_{y}=0\end{align}$$
il sistema diventa dunque $$\begin{align}&\begin{cases}-mg \sin{\Theta}+|\overrightarrow{F}|-\mu_{d}|\overrightarrow{N}|=ma_{x}\\\\ -mg \cos{\Theta}+|\overrightarrow{N}|=0\end{cases}\implies \begin{cases}|\overrightarrow{F}|=ma_{x}+mg \sin{\Theta}+ \mu_{d}|\overrightarrow{N}|\\\\|\overrightarrow{N}|=mg \cos{\Theta}\end{cases}\\\\&\begin{cases}|\overrightarrow{F}|=ma_{x}+mg \sin{\Theta}+ \mu_{d}mg \cos{\Theta}\\\\|\overrightarrow{N}|=mg \cos{\Theta}\end{cases}\end{align}$$
da cui $$\begin{align}|\overrightarrow{F}|&=ma_{x}+mg \cdot(\sin{\Theta}+ \mu_{d}\cos{\Theta})=m\Big[a_{x}+g \cdot(\sin{\Theta}+ \mu_{d}\cos{\Theta})\Big]\\\\&=(15kg)\cdot\bigg[\Big(1 \frac{m}{s^{2}}\Big)+ \Big(9.81 \frac{m}{s^{2}}\Big) \cdot\Big(\sin{30°}+ \frac{9}{10}\mu_{s_{min}}\cos{30°}\Big)\bigg]\\\\&\simeq154.7925 N\end{align}$$
## c.) Potenza $P$ esercitata dalla forza 
Per far muovere la cassa con velocità costante è necessario che l'accelerazione sia nulla ($a_{x}=0$). La forza da applicare diventa quindi $$|\overrightarrow{F^{*}}|=mg(\sin{\Theta}+\mu_{d}\cos{\Theta})$$
La potenza esercitata dalla forza è $$\begin{align}P&=|\overrightarrow{F^{*}}|\cdot V=mg(\sin{\Theta}+\mu_{d}\cos{\Theta})\cdot V\\\\
&=(15kg)\cdot\Big(9.81 \frac{m}{s^{2}}\Big) \cdot\Big(\sin{30°}+ \frac{9}{10}\mu_{s_{min}}\cos{30°}\Big)\cdot \Big(2 \frac{m}{s} \Big)\simeq279.585W\end{align}$$
# Problema 2
![[Pasted image 20250717123540.png|center|500]]

## a.) Andamento temporale della velocità del centro di massa 
![[Pasted image 20250717125522.png|center|500]]

Lungo l'asse orizzontale agisce solo la forza di attrito dinamico tra la palla e il piano. Risulta quindi $$F_{dx}=-\mu_{d}|\overrightarrow{N}|=-\mu_{d}Mg$$
Applicando la seconda legge della dinamica $$Ma_{x}=-\mu_{d}Mg\implies a_{x}=-\mu_{d}g$$
Nell'istante in cui la palla incontra il terreno con attrito, la velocità istantanea del centro di massa varia secondo la legge seguente $$V_{CM,x}=V_{0}+a_{x}t=V_{0}-\mu_{d}gt$$
## 