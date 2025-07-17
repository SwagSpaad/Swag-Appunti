# Problema 1
![[Pasted image 20250715181157.png|center|500]]
![[Pasted image 20250715181216.png|center|500]]

## a.) Accelerazione $a_{x}$ lungo il piano
Da ipotesi il piano è liscio, quindi l'attrito è assente. Osserviamo che lungo l'asse $x$ le componenti sono le seguenti $$(m \overrightarrow{g})_{x}=mg\sin\Theta\qquad N_{x}=0$$
L'unica forza agente sul corpo lungo l'asse $x$ è la forza peso, quindi per la seconda legge della dinamica vale $$ma_{x}=mg\sin\Theta\implies a_{x}=\frac{\cancel{m}g\sin\Theta}{\cancel m}\implies a_{x}=g\sin\Theta$$
sostituendo i valori otteniamo $$a_{x}=\left(9.81 \frac{m}{s^{2}}\right)\cdot\sin30°\simeq4.905 \frac{m}{s^{2}}$$
Il moto è rettilineo uniformemente accelerato.  La velocità istantanea in funzione del tempo è $$V_{x}(t)=V_{x_0}+a_{x}t$$La legge oraria del moto è $$x(t)=x_{0}+V_{x_{0}}t+ \frac{1}{2}a_{x}t^{2}$$
## b.)Accelerazione e forza con $v_{x}(t)=kt^{2}$ 
L'accelerazione è la derivata della velocità istantanea quindi $$a_{x}(t)=V_{x}'(t)=2kt$$
La forza risultante agente sul punto materiale è $$\begin{align}F_{tot_{x}}(t)&=ma_{x}(t)\implies (m \overrightarrow{g})_{x}+F_{x}(t)=ma_{x}(t)\\\\&\implies mg\sin\Theta+F_{x}(t)=ma_x(t)\\\\&\implies F_{x}(t)=m[a_x(t)-g\sin\Theta]\\\\ &\implies F_{x}(t)=m[2kt-g\sin\Theta]\end{align}
$$


## c.) Distanza percorsa $D$ tra $t_{i}=0s$ e $t_{f}=10s$ 
Le condizioni sono quelle del punto b.), quindi con la legge $$V_{x}(t)=kt^{2} \qquad k=2 \frac{m}{s^{3}}$$ la distanza percorsa tra $t_{i}=0s$ e $t_{f}=10s$ è $$\begin{align}D&=\int_0^{t_{f}}V_{x}(t)dt=\int_0^{t_{f}}kt^{2}dt=k\int_0^{t_{f}}t^{2}dt=k \frac{t^{3}}{3}\Bigg|_{0}^{t_{f}}=\frac{1}{3}k(t_{f})^{3}=\\\\&=\frac{1}{3}\cdot 2 \frac{m}{s^{3}} \cdot(10 s)^{3}=666.6 m\end{align}$$

# Problema 2
![[Pasted image 20250715184955.png|center|500]]

![[Pasted image 20250715185419.png|center|500]]

## a.) Velocità angolare $\omega_{i}$ di rotazione
Durante la rotazione, l'energia meccanica si conserva perché la forza peso è conservativa e il perno nell'origine non compie lavoro, perché è applicata a un punto che resta fisso durante la rotazione dell'asta.
Durante la rotazione di $90°$ il centro dell'asta si abbassa di un tratto di lunghezza $\frac{L}{2}$.
Il momento di inerzia dell'asta rispetto l'asse di rotazione considerato è $$I_{z}=\frac{1}{3}ML^{2}$$
Possiamo scrivere $$\begin{align}E_{f}=E_{i}\implies \frac{1}{2}I_{z}\omega_{1}^{2}= \frac{1}{2}MgL\\\\
\cancel{\frac{1}{2}}I_{z}\omega_{1}^{2}=\cancel{\frac{1}{2}} MgL \end{align}$$
sostituendo $I_{z}= \frac{1}{2}ML^{2}$ otteniamo  $$\begin{align}\cancel{\frac{1}{2}}\cdot\frac{1}{3}\cancel{M}{L^{\cancel2}} \omega_{1}^{2}= \cancel {\frac{1}{2}}\cancel{M}g\cancel{L}\implies \frac{1}{3}\omega_{1}^{2}L=g\\\\
\implies \omega_{1}^{2}=\frac{3g}{L}\implies \omega_{1}=\sqrt{\frac{3g}{L}} \end{align}$$
sostituendo numericamente otteniamo $$\omega_{1}=\sqrt{\frac{3\cdot\bigg(9.81 \frac{m}{s^{2}}\bigg)}{5m}}\simeq 2.426 \frac{rad}{s}$$
## b.) Urto con la pallina
Poiché dopo l'urto la pallina rimane attaccata all'estremità dell'asta, il momento d'inerzia del sistema dopo l'urto è $$\begin{align}I_{zf}=I_{zi} +mL^{2}=\frac{1}{3}ML^{2}+mL^{2}=\bigg(\frac{M}{3}+m\bigg)L^{2}\end{align}$$
quindi possiamo scrivere $$I_{zf}\omega_{2}=I_{zi}\omega_{1}\implies\bigg(\frac{M}{3}+m\bigg)\cancel{L^{2}}\omega_{2}=\frac{M}{3}\cancel{L^{2}}\omega_{1}$$
otteniamo quindi $$\begin{align}\omega_{2}=\frac{\frac{M}{3}}{\frac{M}{3}+m}\omega_{1}= \frac{M}{3}\cdot \frac{3}{M+3m}\omega_{1}=\frac{M}{M+3m}\omega_{1}\\\\\implies \omega_{2}=\frac{1kg}{1kg+3\cdot0.1kg}\cdot2.426 \frac{rad}{s}\simeq1.866 \frac{rad}{s}\end{align}$$
## c.) Angolo in cui il sistema si ferma
Dopo l'urto il centro dell'asta si trova a $- \frac{L}{2}$ rispetto al perno e la pallina si trova alla quota $-L$. Nel momento in cui l'asta raggiunge l'angolo $\Theta$ rispetto l'asse verticale, il centro dell'asta si trova a $- \frac{L}{2}\cos{\Theta}$ e la pallina si trova a quota $-L \cos{\Theta}$. Se in questo istante la velocità angolare dell'asta è nulla, allora vale l'equazione $$E_{f}=E_{i}\implies - \frac{1}{2}MgL \cos{\Theta}-mgL \cos{\Theta}= \frac{1}{2}I_{z}\omega_{2}^{2}- \frac{1}{2}MgL-mgL$$
da cui ricaviamo $$\begin{align} \frac{1}{2}I_{z}\omega_{2}^{2}&=- \frac{1}{2}MgL \cos{\Theta}-mgL \cos{\Theta}+ \frac{1}{2}MgL+mgL=\\\\
&=gL\bigg[- \frac{1}{2}M\cos{\Theta}-m \cos{\Theta}+ \frac{1}{2}M+ m\bigg]=\\\\
&=gL\bigg[\frac{1}{2}M(1-\cos{\Theta}) +m(1-\cos{\Theta})\bigg]=\end{align}$$
sostituendo $I_{z}$ al primo membro otteniamo $$\begin{align} \frac{1}{2}\cdot\bigg( \frac{1}{3}ML^{2}+mL^{2}\bigg)\omega_{2}^{2}&=gL\bigg[(1-\cos{\Theta})\cdot\bigg(\frac{1}{2}M+m\bigg)\bigg]\\\\
\frac{1}{2}\cdot L^{\cancel2}\bigg(\frac{1}{3}M+m\bigg)\omega_{2}^{2}&=g\cancel{L} \cdot(1-\cos{\Theta})\cdot\bigg(\frac{1}{2}M+m\bigg)\end{align}$$
da cui otteniamo $$\begin{align}1-\cos{\Theta}&=\frac{L\bigg(\frac{1}{3}M+m\bigg)\omega_{2}^{2}}{2g \cdot\bigg(\frac{1}{2}M +m\bigg)}\\\\
\cos{\Theta}&=1-\frac{L\bigg(\frac{1}{3}M+m\bigg)\omega_{2}^{2}}{2g \cdot\bigg(\frac{1}{2}M +m\bigg)}\\\\
\Theta&=\arccos\Bigg[1-\frac{L\bigg(\frac{1}{3}M+m\bigg)\omega_{2}^{2}}{2g \cdot\bigg(\frac{1}{2}M +m\bigg)}\Bigg]\simeq\arccos(0.359)\simeq1.203 rad\simeq69°\end{align}$$
# Problema 3
![[Pasted image 20250716161742.png|center|500]]

## a.) Modulo forza $F_{0}$ esercitata
Tra le due cariche agisce una forza elettrostatica e secondo la legge di Coulomb $$\begin{align}F_{0}=K_{e} \frac{|Q_{1}|\cdot|Q_{2}|}{x_{2}^{2}}&\simeq (8.98755 \cdot10^{9} \frac{N \cdot m^2}{C^{2}})\cdot\frac{(2.5\cdot10^{-5}C)\cdot(5\cdot10^{-6}C)}{(3.2m)^{2}}\\\\&\simeq0.1097N\end{align}$$
Le due cariche elettriche hanno segno opposto quindi la forza è attrattiva

## b.) Coordinate $x_{0}$ dove modulo del campo elettrico è nullo

![[Pasted image 20250716162415.png|center|500]]

Nella regione $0<x<x_{2}$ i campi elettrici sono non nulli per cui in quella regione il campo elettrico totale non può annullarsi.
Considero le regioni $x<0$ e $x>x_{2}$

Per $x<0$, fisso un punto $x_{A}$ in cui il campo generato da $Q_1$ ha segno negativo mentre quello generato da $Q_{2}$ ha segno positivo

## c.) Modulo velocità $v_{2}$ del corpo con carica negativa
Dato che l'unica forza agente sulle due cariche è la forza elettrostatica, l'energia meccanica si conserva, per cui $$\begin{align}E_{f}=E_{i}\implies \frac{1}{2}m_{2}V_{2}^{2}- K_{e} \frac{Q_{1}\cdot|Q_{2}|}{\frac{x_{2}}{2}}=-K_{e} \frac{|Q_{1}|\cdot|Q_{2}|}{x_{2}}\end{align}$$
otteniamo quindi $$\begin{align} \frac{1}{2}m_{2}V_{2}^{2}&=K_{e}\cdot Q_{1}\cdot|Q_{2}|\bigg[\frac{1}{\frac{x_{2}}{2}}- \frac{1}{x_{2}}\bigg]\\\\
&=K_{e}\cdot Q_{1}\cdot|Q_{2}|\bigg[\frac{2}{x_2}- \frac{1}{x_{2}}\bigg]\\\\
\frac{1}{2}m_{2}V_{2}^{2}&=\frac{K_{e}\cdot Q_{1}\cdot|Q_{2}|}{x_{2}}\end{align}$$
ricaviamo $V_{2}^{2}$ e otteniamo $$\begin{align}V_{2}^{2}&=\frac{2\cdot K_{e}\cdot Q_{1}\cdot|Q_{2}|}{m_{2}\cdot x_{2}}\implies V_{2}=\sqrt{\frac{2\cdot K_{e}\cdot Q_{1}\cdot|Q_{2}|}{m_{2}\cdot x_{2}}}\\\\&=\sqrt{\frac{2\cdot (8.98755 \cdot10^{9} \frac{N \cdot m^{2}}{C^{2}})\cdot(2.5\cdot10^{-5}C)\cdot(5\cdot10^{-6}C)}{(10kg)\cdot(3.2)}}\simeq 0.265 \frac{m}{s} \end{align}$$