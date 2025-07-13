# Problema 1.
![[Problema1.png |Center|500]]

Le forze risultanti sono:
![[Figura1.png | Center | 500]]

### a) Espressione $V_{0}$
Durante la rotazione dell’asta rigida, le forze agenti sulla pallina sono la forza peso della palla e la forza esercitata sulla pallina dall’asta nel punto in cui la pallina è attaccata all’asta. 
Quest’ultima forza compie lavoro nullo in quanto la pallina si muove lungo la circonferenza di raggio $L$, mentre la forza esercitata dall’asticella è diretta lungo il raggio della traiettoria, per cui a ogni istante è perpendicolare alla direzione della velocità istantanea della palla.
Dunque, l’unica forza che compie lavoro sulla palla è la forza peso; dato che la forza peso è conservativa, ed essendo l’unica forza che compie lavoro, concludiamo che l’energia meccanica della palla si conserva durante tutto il moto della palla. Facciamo riferimento allo schema riportato sopra: all’istante in cui la pallina si trova nel punto $A$, la sua quota verticale è  
$y_{A}=0$, e la sua velocità è $\overrightarrow{V_{0}}$ ; all’istante in cui la palla si trova nel punto $D$, la sua quota verticale è  
$y_{D}=L$ e la sua velocità è  $\overrightarrow{V_{D}} = 0$.
Pertanto imponiamo che l'energia meccanica della palla in $A$ e in $D$ sia la stessa.

$$E_{m,a} = E_{m,D} \implies K_{A} + U_{A} = K_{D} + U_{D}$$
Risulta:
$$K_{A} =  \frac{1}{2} m|\overrightarrow{V_{A}}| = \frac{1}{2} mV_{0}^{2}; \qquad U_{A } = 0$$
$$K_{D} =  \frac{1}{2} m|\overrightarrow{V_{D}}|^{2} = 0; \qquad U_{D} = mgL$$
Dunque, vale l'uguaglianza:
$$\frac{1}{2}\cancel{m}V_{0}^2 = \cancel{m}gL \implies V_{0}^{2} = 2gL$$
E quindi:
$$V_{0} = \sqrt{2gL} = \sqrt{2\cdot(9,81\frac{m}{s^{2}}) \cdot (0,5m)} \simeq 3,132 \frac{m}{s}$$ 
### b) Modulo $T_{B}$ istante punto $B$
Nell'istante in cui la palla passa per il punto $B$ il diagramma delle forze agenti sulla palla é il seguente:
![[puntoB.png |center|400]]

Fissata la direzione positiva dell'asse y, risulta:
$$(m\overrightarrow{g})_{y} = -mg; \qquad (\overrightarrow{T}_{B})_{y} = |\overrightarrow{T}_{B}| = T_{B}$$ $\overrightarrow{T}_{B}$ é la forza esercitata sulla palla dall'asticella nel punto $B$ .
Per la **seconda legge della dinamica** risulta quindi:

$$m\overrightarrow{a_{B}} = m\overrightarrow{g} \ + \ \overrightarrow{T_{B}} $$
cioé:
$$m(\overrightarrow{a_{B}})_{y} = (m\overrightarrow{g})_{y} \ + \ (\overrightarrow{T_{B}})_{y} \implies ma_{B,y} = -mg \ + \ T_{B}$$

Ma $a_{B,y}$ é la componente radiale dell'accellerazione istantanea della palla nel punto $B$:
questa accellerazione é l'accellerazione centripeda della palla in quel punto, allora:
$$a_{B,y} = \frac{|\overrightarrow{V_{B}}|^{2}}{L}$$
Per calcolare $|\overrightarrow{V_{B}}|^2$ , sfruttiamo ancora la conservazione dell'energia meccanica:
$$E_{m,B} = E_{m,A} \implies K_{B} \ + \ U_{B} = K_{A} \ + \ U_{A}$$
Risulta:
$$K_{B} =  \frac{1}{2} m|\overrightarrow{V_{B}}|^{2}; \qquad U_{B} = -mgL$$
$$K_{A} \ + \ U_{A} = \frac {1}{2}mV_{0}^{2} = \frac{1}{\cancel 2}m\cdot \cancel {2} gL = mgL$$
Allora:
$$\frac{1}{2}m|\overrightarrow{V_{B}}|^{2} -mgL = mgL \implies \frac{1}{2}\cancel{m}|\overrightarrow{V_{B}}|^{2} = 2\cancel{m}gL$$
Segue:
$$|\overrightarrow{V_{B}}|^{2} = 4gL$$
Allora:
$$a_{B,y}= \frac {|\overrightarrow{V_{B}}|^{2}}{L} = 4g$$
Pertanto:
$$ma_{B, y} = -mg \ + \ T_{B} \implies m\cdot 4g = -mg \ + \ T_{B}$$
E infine:
$$T_{B} = 5mg = 5\cdot(0,02kg) \cdot(9,81\frac{m}{s^{2}})\simeq 0,981N$$

### c) $\Delta{E}$ palla nell'ultima rotazione
In questa condizione risulta:
$$E_{m,A} = K_{A} \ + U_{A} = mgL$$
(Dato che le condizioni iniziali sono uguali nel punto *a*)
Risulta poi:
$$E_{m,c} = \frac{1}{2}m|\overrightarrow{V_{c}}|^{2} \ + \ U_{c}$$
Essendo $|\overrightarrow{V_{c}}| = 0$ e $y_{c} = 0$, Otteniamo:
$E_{m,c} = 0$, Pertanto risulta:
$$\Delta{E} = E_{m,c} - E_{m,a} = 0-mgL = -mgL = -(0,02kg)(9,81\frac{m}{s^2})(0,5m) = -0,0981J$$

# Problema 2.
![[Problema2.png | center | 500]]

Le forze risultanti sono:
![[puntoA.png | center | 400]]

### a) Modulo $T$ tensione cavetto
Per l'equilibrio statico del sistema devono essere verificate le seguenti due condizioni:
- Equilibrio delle forze esterne agenti sul sistema;
- Equilibrio dei momenti delle forze esterne agenti sul sistema, rispetto a un qualunque punto fisso scelto come polo per il calcolo dei momenti

- Equilibrio delle forze esterne: $m\overrightarrow{g} \ + \ \overrightarrow{R} \ + \ \overrightarrow{T} = 0$
- Equilibrio dei momenti delle forze esterne rispetto al polo 0: $(\overrightarrow{OA} \times \overrightarrow{T})_{O} \ + \ (\overrightarrow{Oc} \times m\overrightarrow{g})_{O} = 0$ 
Componenti delle forze lungo gli assi cartesiani:
$$(m\overrightarrow{g})_{x} = 0; \qquad (m\overrightarrow{g})_{y} = -mg; \qquad (\overrightarrow{R})_{x} = R_{x}; \qquad (\overrightarrow{R})_{y} = R_{y}; \qquad (\overrightarrow{T})_{x} = T_{x}; \qquad (\overrightarrow{T})_{y} = T_{y}$$ Allora:
$$m\overrightarrow{g} \ + \ \overrightarrow{R} \ + \ \overrightarrow{T} = 0 \implies 
\begin{cases}
(m\overrightarrow{g})_{x} \ + \ (\overrightarrow{R})_{x} \ + \ (\overrightarrow{T})_{x} = 0 \\
(m\overrightarrow{g})_{y} \ + \ (\overrightarrow{R})_{y} \ + \ (\overrightarrow{T})_{y} = 0
\end{cases}
\implies
\begin{cases}
0 \ + \ R_{x} \ + \ T_{x} = 0 \\
-mg \ + \ R_{y} \ + \ T_{y} = 0
\end{cases}$$
I momenti delle due forze $m\overrightarrow{g}$  e $\overrightarrow{T}$  rispetto al polo $O$  sono diretti perpendicolarmente al piano della figura, con:
$$(\overrightarrow{OC} \times m\overrightarrow{g})_{O,Z} < 0 \qquad e \qquad (\overrightarrow{OA} \times \overrightarrow{T})_{O,Z} > 0$$ Il braccio di $m\overrightarrow{g}$  rispetto al polo $O$ é:
$$d-\frac{l}{2} \implies (\overrightarrow{OC} \times m\overrightarrow{g})_{O,Z} = -mg(d-\frac{l}{2})$$
Risulta poi:
$$(\overrightarrow{OA} \times \overrightarrow{T})_{O,Z} = dTy$$
Allora:
$$(\overrightarrow{OA} \times \overrightarrow{T})_{O,Z} \ + \ (\overrightarrow{OC} \times m\overrightarrow{g})_{O,Z} = 0 \implies dTy-mg(d - \frac{1}{2}) = 0$$
Dato che $\overrightarrow{T}$  é diretto lungo il cavetto fissato al muro, se indichiamo con $\alpha$ l'angolo tra il cavetto e l'asta orizzontale (vedi figura) risulta:
$$T_{x} = -|\overrightarrow{T}| cos\alpha; \qquad Ty = |\overrightarrow{T}| sin\alpha \implies |\overrightarrow{T}| = \frac{Ty}{sin\alpha}$$
Dall'ultima equazione relativa all'equilibrio dei momenti otteniamo:
$$Ty = mg(1-\frac{1}{2d})$$
Da cui, essendo:
$$sin_{\alpha} = \frac{tan\alpha}{\sqrt{1+tan^{2}\alpha}} = \frac{\frac{h}{d}}{\sqrt{1+(\frac{h}{d})^{2}}} = \frac{d}{\sqrt{h^{2} \ + \ d^{2}}}$$
Quindi:
$$\begin{align}T = |\overrightarrow{T}| &= mg\bigg(1-\frac{l}{2d}\bigg) \frac{1}{h} \sqrt{h^{2} \ + \ d^{2}} = mg\bigg(1-\frac{l}{2d}\bigg) \sqrt{1+\bigg(\frac{d}{h}\bigg)^{2}}\\\\ &= (50kg)\bigg(9,81 \frac{m}{s^{2}}\bigg)\bigg(1 - \frac{2m}{6m}\bigg)\sqrt{1 \ + \ \bigg(\frac{3m}{4m}\bigg)^{2}} \simeq 408,75N\end{align}$$

### b) $R_{x}$ e $R_{y}$
Dalle equazioni che esprimono l'equlibrio delle forze esterne, otteniamo:
$$R_{x} = -T_{x} = |\overrightarrow{T}|gs \alpha = \frac{T_{y}}{tan\alpha} = T_{y}\cdot \frac{d}{h} = mg\bigg(1-\frac{l}{2d}\bigg)\cdot \frac{d}{h}$$
$$R_{x} = mg\frac{1}{h}\bigg(d-\frac{l}{2}\bigg) = (50kg)\bigg(9,81 \frac{m}{s^2}\bigg) \frac{1}{4m}\bigg(3m-\frac{1}{2}\cdot2m\bigg) = 245,25N$$
E:
$$R_{y} = mg-T_{y} = mg\bigg[1-\bigg(1-\frac{l}{2d}\bigg)\bigg] = mg\bigg(\cancel{1}-\cancel{1}+\frac{l}{2d}\bigg)$$
$$R_{y} = mg\frac{l}{2d} = (50kg)\bigg(9,81\frac{m}{s^2}\bigg)\cdot\frac{2m}{2\cdot3m} = 163,5N$$
###  c) Modulo $R$ forza reazione
Risulta:
$$\begin{align}|\overrightarrow{R}| &= \sqrt{R^{2}_{x} + R^{2}_{y}} = \sqrt{(mg)^{2}[(\frac{d}{h})^{2}(1-\frac{l}{2d})^{2}(\frac{l}{2d})^{2}]} = mg\sqrt{(\frac{d}{h})^{2}(1-\frac{l}{2d})^{2}(\frac{l}{2d})^{2}} \\\\&= \sqrt{(245,25N)^{2}+(163,5N)^{2}} \simeq 294,754N\end{align}$$
Infine, $\Theta$, l'angolo tra il vettore $\overrightarrow{R}$ e l'asta, risulta:
$$\begin{align}tan\Theta &= \frac{R_{x}}{R_{y}} = \frac{\cancel{mg}\frac{l}{2d}}{\cancel{mg}\frac{1}{h}(d-\frac{l}{2})} = \frac{lh}{2d(d-\frac{l}{2})}= \\\\&= \frac{lh}{d(2d-l)} \implies \Theta = arctan[\frac{lh}{d(2d-l)}] \simeq 0,588rad\end{align}$$

# Problema 3.
![[Problema3.png|center|500]]

Le forze risultanti sono:
![[Figura3.png|center|400]]

### a) F.E.M e direzione corrente
Scelto un asse cartesiano $x$ come nella figura sopra, osserviamo che nell'intervallo di tempo $\Delta{t}$ il flusso magnetico concatenato dalle due rotaie e dalle sbarra varia dalla quantitá:
$$\Delta\Phi_{B} = B\cdot\Delta{S}$$Dove, $\Delta{S}$ é la variazione dell'area racchiusa dalle due rotaie e dalle sbarre in tale intervallo di tempo;
Risulta quindi:
$$\Delta\Phi_{B} = B\cdot Lv\Delta{t}$$
da cui:
$$\frac{\Delta\Phi_{B}}{\Delta{t}} = BLv$$
Per la legge di Faraday-Neumann, quindi, mentre la sbarra si muove si genera una F.E.M indotta nel circuito da:
$$\xi = -\frac{d\Phi_{B}}{dt} = -BLv = -(0,350T)(0,25m)(0,55\frac{m}{s}) \simeq -0,048125V$$
Per la legge di Lens, la corrente deve circolare in senso orario per compensare l'aumento del flusso concatenato.

### b) $I$ indotta
Se la resistenza della sbarra é $R = 18\Omega$ e se le rotaie hanno resistenza trascurabile, la resistenza complessiva del circuito é $R$, per cui la corrente indotta nel circuito é:
$$I = \frac{|\xi|}{R} = \frac{0,048125V}{18\Omega}\simeq 2,6736\cdot 10^{-3}A = 2,6736mA$$
### c) Potenza elettrica dissipata
La potenza elettrica dissipata dalla sbarra é quindi:
$$P_{d} = \frac{\xi^{2}}{R} = \frac{(0,048125V)^{2}}{18\Omega} \simeq 1,286675\cdot 10^{-4}W = 0,1287mW$$